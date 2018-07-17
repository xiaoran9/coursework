import sys
from  nltk.corpus import brown, conll2000, alpino, dependency_treebank,treebank
from collections import defaultdict
from hmm import data_processing
from viterbi import viterbi
import time
'''
the main file ask user input the corpus they want to test (we use 90% corpus to train and test the next 10%)
different copus accuracy is :
brown: Accuracy rate:91.29838016806018%
conll2000: Accuracy rate: 88.68818363888461%
alpino: Accuracy rate: 84.39314646211199%
dependency_treebank : Accuracy rate: 83.61317051897078%
treebank :Accuracy rate:84.70229007633587%
'''
def select_corpus(corpus):
    if corpus == '1':
        sent = brown.tagged_sents(tagset ='universal')
    elif corpus == '2':
        sent = conll2000.tagged_sents()
    elif corpus == '3':
        sent = alpino.tagged_sents()
    elif corpus == '4':
        sent = dependency_treebank.tagged_sents()
    elif corpus == '5':
        sent = treebank.tagged_sents()
    else:
        print("you input wrong arg, it shoule be : python main.py numCorpus numSmoothing")
        sys.exit(0)
    return sent

corpus = sys.argv[1]
smoothing = sys.argv[2]
sent =select_corpus(corpus)

print("-----------------------------------------start:",time.clock())
training_sents = []
test_sents = []
transition = defaultdict(dict)
emission = defaultdict(dict)

emission ,transition, emission_2 = data_processing(sent,corpus,smoothing)

viterbi(transition,emission,emission_2,sent)
print("-----------------------------------------finish:",time.clock())
