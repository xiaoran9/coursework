import nltk
import sys
from nltk.corpus import brown
from nltk.probability import (FreqDist,ConditionalFreqDist)
'''
we calculate the biterbi use tow different emission,
when the word is unknown, use the emission2 which have UNK
So there are 4 situation:
1current and previous are known
2current known , but previous is unknown
3 current is unknown, precious is known
4 current and previous all unknon

this accurancy calculate the number of correct tags in one test sentence
and add all sentens's corrct number to calculate the totoal accurancy
'''
def viterbi(trans_Prob,emiss_Prob,emiss_Prob_2,sents):
    sent_accuracy = []
    totoal_tag = [] #all test sentences'tags . the number of tag eual to word in test sentences
    correct={} # dictinary of each tag's correct number the key is the tag and value is correct tag number untill current testing sentence
    lenth = int(len(sents)*0.9) # only test the last ten percent sentences
    for sen in sents[lenth:len(sents)]:
        words = []
        tags = []
        totoal_tag += [t for (_,t) in sen]
        tags =[t for (_,t) in sen]
        words = [w for (w,_) in sen]

        ########initialisation of viterbi
        viterbi = [{}]
        path = {}
        if words[0] in emiss_Prob:
            for tag in emiss_Prob[words[0]]:
                viterbi [0][tag] = trans_Prob["<s>"][tag] * emiss_Prob[words[0]][tag]
                path[tag] =[tag]
        else: #if the first word is unknown word
           for tag in emiss_Prob_2["UNK"]:
                viterbi [0][tag] = trans_Prob["<s>"][tag] * emiss_Prob_2["UNK"][tag]
                path[tag] =[tag]

        #########recursion viterbi with 4 situation
        for n in range (1, len(words)):
            viterbi.append({})
            newpath = {}
            # prev_viterbi[ Y ] * P(X | Y) * P( w | X)
            if words[n] in emiss_Prob:
                if words[n-1] in emiss_Prob:
                    viterbi,newpath = recursion( emiss_Prob[words[n]] , emiss_Prob[words[n-1]], n , viterbi,path) #situation1
                else:
                    viterbi,newpath = recursion( emiss_Prob[words[n]],emiss_Prob_2["UNK"],n,viterbi,path) #situation2
                path = newpath
            else:
                if words[n-1] in emiss_Prob:
                    viterbi,newpath = recursion( emiss_Prob_2["UNK"] , emiss_Prob[words[n-1]], n , viterbi,path) #situation3
                else:
                    viterbi,newpath = recursion( emiss_Prob_2["UNK"],emiss_Prob_2["UNK"],n,viterbi,path) #situation4
                path = newpath

        ######### terminal step
        if words[len(words)-1] in emiss_Prob:
            (probability,tag_state) = max([(viterbi[len(words)-1][tag], tag) for tag in emiss_Prob[words[len(words)-1]]])
        else:
            (probability,tag_state) = max([(viterbi[len(words)-1][tag], tag) for tag in emiss_Prob_2["UNK"]])

        #########calculat the number of correct tags in one sentence
        correct = (accuracy(words,tags,path,tag_state,correct))
    #########calculate the accuracy
    tag_frequency = FreqDist(totoal_tag)
    total = 0
    for tag in correct:
        print(tag,' accuracy:',correct[tag]/float(tag_frequency[tag])*100,'%')
        total += int(correct[tag])
    print("Accuracy rate: ", total/float(len(totoal_tag))*100,'%')
    return (probability, path[tag_state])

'''
camparation of the "gold-standard" wiht the original tag
if it's correct add  one to the associated tag's
'''
def accuracy(sent,tags,path,terminal,correct_word):
    for n in range(0,len(sent)):
        if path[terminal][n] == tags[n]:
            if tags[n] in correct_word: #avoid the situation the don't have such key
                correct_word[tags[n]] +=1
            else:
                correct_word[tags[n]] =1
    return correct_word

'''
this function is the recursion part of Viterbi
w_t is the probability of every tag matching current word
the dictionary, the keu is tag with value of probability
last_w_t is the probability of every tag matching previous word in testing sentence
N is the n-th word in sentence
when calculate a list of curretn word' probability of tag
the formula of viterbi[n-1][last_tag] * trans_Prob[last_tag][tag] * emiss_Prob[word][tag]
but we only need the max one of them, the emiss_Prob[word][tag] is multiply every times
So i delet it to reduce the calculation
'''
def recursion(w_t,last_w_t,n,viterbi,path):
    newpath = {}
    for tag in w_t:
        if tag =="<s>":continue
        #the max(number,b) will return the max number and the mached letter
        (probability,tag_state) = max([(viterbi[n-1][last_tag] * w_t[tag], last_tag) for last_tag in last_w_t ])
        viterbi[n][tag] = probability
        newpath[tag] = path[tag_state]+[tag]
    return viterbi,newpath
