#Hidden markov models in python
from nltk import ngrams
from nltk.corpus import brown
from nltk.probability import FreqDist
from collections import defaultdict
import math
import sys
'''
    In this hmm, i implement one transition dictionary and two emission dictionary
    emission 1 is the basic emission dictionary,
    the emission 2 is replace the word that occur once in the trainning sentences to UNK
    Then new training sentence created and train it again to get the second emission
    There two smoothing methods for transition probability: one is Laplace and the other is good turing
'''
def data_processing(sents,corpus,smoothing):
    A=[0.69,5,3,5.1,5.1]
    B = [-0.1,-0.8,-0.9,-0.9,-.95]
    START ="<s>"  #the boundary of one sentence
    END ="</s>"
    N =2  # this is the n of N-grams
    tags =[] # a sequence of tag in the trainning sentences
    words =[] # a sequence of word appear in the trainning sentences
    tag_pair =[] # the pair of N number of tag, in this Hmm, it's 2 biagrams
    sentences = []
    #######preparing data for probability
    lenth = int(len(sents)*0.9)
    for sen in sents[0:lenth]: #add start and end boundary to each sentences and put them in one sentence
        sentences += [START] +sen + [END]
        tags += [START] + [t for (_,t) in sen] + [END]
        words += [w for (w,_) in sen]

    distinct_tag = set(tags) #get every tag once no mater how many times it appear
    tag_pair = ngrams(tags,N) # get tow neighbor tags as a pair and savd the
    freqs_tags = FreqDist(tag_pair) # calculate the same tag pair appear times. it also the c(t{i-1},t) in calculate transition probability
    freqs_tag = FreqDist(tags) # the frequence of one tag. in transition probability is C(t{i-1})

    #########calculate transition probabblility
    N_1 = {}
    N ={}
    N_1,N = countFreqency(freqs_tags,A[int(corpus)-1],B[int(corpus)-1])
    transition = defaultdict(dict)
    transition = transition_dict(distinct_tag,freqs_tag,freqs_tags,N_1,smoothing)

    #########calculate emission probabblility
    emission = defaultdict(dict) # create a dictionar emission to save the normal emission
    distinct_word = set(words) # this dictinct word don't have the word's UNK
    emission = emission_dict(sentences,distinct_word,freqs_tag,distinct_tag)

    #########Replace to UNK
    unknon_word =[] #create a list to save the new word whith replce all word appear once
    sentences, unknon_word = replace(words,sents)
    distinct_word = set(unknon_word)
    emission_2 = defaultdict(dict)
    emission_2 = emission_dict(sentences,distinct_word,freqs_tag,distinct_tag)

    return emission, transition,emission_2

def transition_dict(distinct_tag,freqs_tag,freqs_tags,N,smoothing):
    transition = defaultdict(dict)
    for past_key in distinct_tag: # with to loop of distinc tag to get all likelyhood of tow tags
        transition.setdefault(past_key,0)
        new_list = {}
        for key in distinct_tag:
            new_list.setdefault(key,0)
            if smoothing == '1':
                new_list[key] = Laplace(key,past_key,freqs_tag,freqs_tags) # in this list, the key is the tag of current tag and the probability is this tag with it's previous tag
            elif smoothing == '2':
                new_list[key] = good_turing(key,past_key,freqs_tags,N)
            elif smoothing == '3':
                new_list[key] = transition_Prabability(key,past_key,freqs_tag,freqs_tags)#transition probability without the Laplace smoothing
            else:
                print("you input wrong arg, it shoule be : python main.py numCorpus numSmoothing")
                sys.exit(0)

            transition[past_key] = new_list # for each key it macth a diction of tag with probability
    return transition

#r tims' tags appear times
# the frequency of frequency (tag-tag)
def countFreqency(freqs_tags,A,B):
    N = {}
    N_1 = {} #=A+Blog(c)
    count_tags = []
    for key in freqs_tags:
        count_tags.append(freqs_tags[key]) #times key appears,

    for r in range(1,max(count_tags)+2):
        count_r = 0
        for i in count_tags:
            if i != r: continue
            count_r += 1
        N [r] = count_r #how many pair appear r times
        N_1[r] = math.exp(A+B*math.log(r))
    return N_1,N

'''
	find all words with all tags
	then delete the situation that word match tag not appear in the training sentence
	so we can get the POSs that have been seen for that word
'''
def emission_dict(sentences,distinct_word,freqs_tag,distinct_tag):
    freqs_W_T = FreqDist(sentences) ##caoculate the frequence of word and tag
    emission = defaultdict(dict)
    for word in distinct_word:
        emission.setdefault(word,0)
        new_list = {}
        for tag in distinct_tag:
            probability = emission_probability(word,tag,freqs_tag,freqs_W_T)
            if probability>0: # if the probablitiy eqaul to 0 means the word and tag not seen in the POS
                new_list.setdefault(tag,0)
                new_list[tag] = probability
        emission[word] = new_list
    return emission
'''
replace the UNK and create a new sentence of(word,tag)
'''
def replace(words,sents):
    new_sents = []
    new_words=[]
    START ='<s>'
    END ='</s>'
    freqs_word = FreqDist(words)
    for sen in sents[0:1000]:
        new_sents +=[END]+[START]
        for key in sen:
            if freqs_word[key[0]] == 1:
                new_sents += [("UNK",key[1])]
            else:
                new_sents += [key]
                new_words += [key[0]]
    new_words += ["UNK"]

    return new_sents ,new_words
'''
With the given frequence , to calculate P(Wi|Ti)=C(Ti,Wi)/C(Ti)
'''
def emission_probability(word,tag,freqs_tag,freqs_W_T):
    if freqs_tag[tag] > 0:         #make sure the denominator bigger than 0
        return freqs_W_T[word,tag]/float(freqs_tag[tag])
    else:
        return 0

'''
With the given frequence , to calculate P(Ti|Ti-1)=C(Ti-1,Ti)/C(Ti-1)
'''
def transition_Prabability(current,past,freqs_tag,freqs_tags):
    if freqs_tag[past] > 0:         #make sure the denominator bigger than 0
        return freqs_tags[past,current]/float(freqs_tag[past])
    else:
        return 0
'''
the smoothing is change the P(Ti|Ti-1)=C(Ti-1,Ti)/C(Ti-1) to P(Ti-1|Ti)=C(Ti-1,Ti)+1/C(Ti-1)+V
the V is the size of tag
Beacuse the freqs_tag contain all the distinc tag,it can be seen as the size of tag
'''
def Laplace(current,past,freqs_tag,freqs_tags):
    posibility = (freqs_tags[past,current])+1/float(freqs_tag[past]+len(freqs_tags))
    return posibility

def good_turing(current,past,freqs_tags,N):
    if freqs_tags[past,current] == 0: #this tag pari never seen before that
        posibility = N[1]/len(freqs_tags) #
    else:
        posibility = (freqs_tags[past,current]+1)*N[freqs_tags[past,current]+1]/len(freqs_tags)*N[freqs_tags[past,current]]
    return posibility
