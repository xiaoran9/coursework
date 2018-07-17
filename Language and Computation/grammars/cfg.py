from nltk import CFG, ChartParser
cfg = CFG.fromstring("""\
S   -> NP VP |Aux NP VP |WH Aux S|Clause S
NP  -> NOM Conj NP | NOM |Det NOM 
VP  -> V | V NP | Adv VP| V NP VP| VP PP |V NP NP
NOM -> Noun| ProperNoun |Adj NOM
PP  -> Prep NP
Clause -> Comp S

ProperNoun -> 'Homer' | 'Bart'|'Lisa'
V -> 'laughs' | 'runs' | 'laughed' |'drink'|'drinks'|'wears'|'wear'|'serves'|'thinks'
Noun -> 'milk'|'shoes'|'salad'|'kitchen'|'midnight'|'table'
Det -> 'a'|'the'
Prep -> 'in'|'before'|'on'
Adj ->'blue'|'healthy'|'green'
Adv -> 'always'|'never'
Conj ->'and'
Aux -> 'do'|'does'
WH ->'when'
Comp ->'when'
""")
cfparser = ChartParser(cfg)
text = """\
Bart laughs
Homer laughed
Bart and Lisa drink milk
Bart wears blue shoes
Lisa serves Bart a healthy green salad
Homer serves Lisa
Bart always drinks milk
Lisa thinks Homer thinks Bart drinks milk
Homer never drinks milk in the kitchen before midnight
when Homer drinks milk Bart laughs
when does Lisa drink the milk on the table
when do Lisa and Bart wear shoes
"""
sents = text.splitlines()
for sent in sents:
    parses = cfparser.parse(sent.split())
    print(sent)
    for tree in parses:
		print(tree)