RWADME
this program creating a hidden markov model of POS tagging and smoothing
the hidden markov use the Laplace and good-turing smoothing
the unknwon word is dealing with the thinking that the unknwon words appear's probablity is same to the  word appear compliance

there are 3 python files :
main.py: This is a main python file which control hmm.py and viterbi.pyi
hmm.py: this file trainning the corpus and generating the model
viterbi.py this is using to test the trained hmm and calculate the accuracy

Usage:
main.py: Need 2 commandline argument. the first is for which corpus you wanted and secoend is which soothing you need 
        it apply 5 corput and 2 smoothing  to train and test
        1, brown |2 conll2000|3 alpino|4 dependency_treebank|5 treebank
	1,Laplace,2good_turing 3 without smoothing

e.g. using the brown and good-turing 
	python main.py 1 2 

