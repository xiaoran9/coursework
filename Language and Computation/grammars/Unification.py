from nltk.grammar import FeatureGrammar
from nltk import FeatureChartParser

ugrammar = FeatureGrammar.fromstring("""\
############################  nomal sentense  #######################################
S -> NP[NUM=?n] VP[FORM=?f,NUM=?n,SUBCAT=nil] 
S -> CLAUSE S
S -> WhNP Aux[NUM=?n,FORM=?f] QS[NUM=?n]

############################ questions sentence ###############################
QS[NUM=?n] -> NP[NUM=?n] VP[FORM=base]

############################ Wh subject questions  ###############################
WhNP -> Wh NP|Wh

############################  VP  #######################################
VP[FORM=?f,NUM=?n,SUBCAT=?args] -> V[FORM=?f,NUM=?n,SUBCAT=?args] 
VP[FORM=?f,NUM=?n,SUBCAT=?rest] -> VP[FORM=?f,NUM=?n,SUBCAT=[HEAD=?arg,TAIL=?rest]] ARG[CAT=?arg] 

VP[FORM=?f,NUM=?n] -> VP[FORM=?f,NUM=?n] ADJC 
VP[FORM=?f,NUM=?n] -> Adv VP[FORM=?f,NUM=?n]
VP[FORM=?f,NUM=?n] -> Mod VP[FORM=base] | Mod PASTP[FORM=?f] | Mod 'not' PASTP[FORM=?f]
VP[FORM=?f,NUM=?n] -> PASTP[FORM=?f]
VP[FORM=?f,NUM=?n] -> Aux[FORM=?f] VP[FORM=?f]

############################  PASTP  #######################################
PASTP[FORM=?f] -> Aux[FORM=?f] VP[FORM=?f]

############################  ARG  #######################################
ARG[CAT=np]-> NP[NUM=?n] 
ARG[CAT=pp]-> PP
ARG[CAT=clause]-> S
ARG[CAT=clause]-> CLAUSE

############################  ADJUNCT  #######################################
ADJC[CAT=pp] ->PP

############################# CLAUSE #############################
CLAUSE -> Comp S

############################  NP  #######################################
NP[NUM=?n]   -> NOM[NUM=?n] 
NP[NUM=?n]   -> Det[NUM=?n] NOM[NUM=?n] 
NP[NUM=plur] -> NP Conj NP 
NP[NUM=?n]   -> VP[FORM =prespart] 

############################  NOM  #######################################
NOM[NUM=?n] -> Noun[NUM=?n]| Noun[NUM=?n] ADJC
NOM[NUM=?n] -> ProperNoun[NUM =?n] |ProNoun[NUM=?n]
NOM[NUM=?n] -> Adj NOM[NUM=?n]

############################  PP  #######################################
PP -> Prep NP

############################  word  #######################################
#-------------vi--------------------#
V[FORM=vbz,NUM =sing,SUBCAT=nil]  -> 'laughs' 
V[FORM=base,NUM =plur,SUBCAT=nil] -> 'laugh' 

#--------------------------   vt + np   ---------------------------------#
V[FORM=vbz,NUM =sing,SUBCAT=[HEAD=np,TAIL=nil]]  -> 'wears' | 'drinks'|'serves'|'likes'
V[FORM=base,NUM =plur,SUBCAT=[HEAD=np,TAIL=nil]] -> 'wear'  | 'drink' |'serve'

#--------------------------vt + np + np ---------------------------------#
V[FORM=vbz,NUM =sing,SUBCAT=[HEAD=np,TAIL=[HEAD=np,TAIL=nil]]] -> 'serves'

#--------------------------vt + np + pp ---------------------------------#
V[FORM=vbz,NUM =sing,SUBCAT=[HEAD=np,TAIL=[HEAD=pp,TAIL=nil]]] -> 'puts'

#-------------------------- vt + clause ---------------------------------#
V[FORM=vbz,NUM =sing,SUBCAT=[HEAD=clause,TAIL=nil]]  -> 'thinks'
V[FORM=base,NUM =plur,SUBCAT=[HEAD=clause,TAIL=nil]] -> 'think'

#--------------------------   v-past    ---------------------------------#
V[FORM=pret,SUBCAT=nil] -> 'laughed' 

#--------------------------v-past Participle-----------------------------#
V[FORM=pastpart,SUBCAT=[HEAD=np,TAIL=nil]] -> 'drunk'
V[FORM=pastpart,SUBCAT=[HEAD=np,TAIL=np]]  -> 'seen'

#-------------------------- v-GERUND    ---------------------------------#
V[FORM=prespart,SUBCAT=[HEAD=np,TAIL=nil]] -> 'drinking'

#--------------------------      Noun    ---------------------------------#
Noun[NUM =sing]       -> 'milk' |'salad' |'kitchen'|'midnight'|'table'|'bread'
Noun[NUM =plur]       -> 'shoes'
ProperNoun[NUM =sing] -> 'Homer'| 'Bart'| 'Lisa'
ProNoun[NUM=sing]     -> 'he'

#--------------------------      AUX       -------------------------------#
Aux[FORM=base,NUM =plur]     -> 'do'
Aux[FORM=vbz,NUM =sing]      -> 'does'
Aux[FORM=pret]               -> 'did'
Aux[FORM=pastpart, NUM=sing] -> 'has'
Aux[FORM=pastpart, NUM=plur] -> 'have'


#--------------------------      others    -------------------------------#
Conj           ->'and'
Wh             -> 'when'| 'what' | 'where' | 'whom'
Mod            -> 'may' 

Det[NUM =sing] -> 'a'|'the'
Det[NUM =plur] -> 'the'

Prep           -> 'in'    |'before'  |'on'
Adj            ->'blue'   |'healthy' |'green'
Adv            -> 'always'|'never'|'not'
Comp           -> 'when'


""")
uparser = FeatureChartParser(ugrammar)
text = """\
Bart laughs                  
Homer laughed               
Bart and Lisa drink milk    
Bart wears blue shoes       
Homer serves Lisa		
Bart always drinks milk		
Lisa thinks Homer thinks Bart drinks milk   
Lisa serves Bart a healthy green salad
Homer never drinks milk in the kitchen before midnight
when Homer drinks milk Bart laughs
when does Lisa drink the milk on the table
when do Lisa and Bart wear shoes
Bart likes drinking milk
Lisa may have drunk milk
Lisa may have seen Bart drinking milk
Lisa may not have seen Bart drinking milk
what does Homer drink
what salad does Bart serve
whom does Homer serve salad
whom do Homer and Lisa serve
what salad does Bart think Homer serves Lisa
he puts bread on the table
Lisa have drunk milk

Bart laugh  
he puts bread bread
what does Homer drinks
what do Homer drink 
Lisa may drunk milk
"""
sents = text.splitlines()
i = 0
for sent in sents:
    parses = uparser.parse(sent.split())
    print('#######################################################')
    i=i+1
    print(i, sent)
    for tree in parses:
		print(tree)
		print('\n')
	
	



