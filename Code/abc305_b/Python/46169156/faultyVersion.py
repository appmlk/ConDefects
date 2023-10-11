p,q=map(str, input().split())
li=[3,4,8,9,14,23]
long=0
short=0
if p=='A':
    short=0
elif p=='B':
    short=li[0]
elif p=='C':
    short=li[1]
elif p=='D':
    short=li[2]
elif p=='E':
    short=li[3]
elif p=='F':
    short=li[4]
elif p=='G':
    short=li[5]

if q=='A':
    short=0
elif q=='B':
    long=li[0]
elif q=='C':
    long=li[1]
elif q=='D':
    long=li[2]
elif q=='E':
    long=li[3]
elif q=='F':
    long=li[4]
elif q=='G':
    long=li[5]
    
print(abs(long-short))