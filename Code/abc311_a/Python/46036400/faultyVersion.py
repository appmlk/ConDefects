import sys
N=int(input())
S=str(input())
a=0
b=0
c=0
n=0
S=list(S)
for i in range(N):
    if a>=1 and b>=1 and c>=1:
        print(n)
        sys.exit()
    elif S[i]=='A':
        n+=1
        a+=1
    elif S[i]=='B':
        n+=1
        b+=1
    elif S[i]=='C':
        n+=1
        c+=1
    else:
        n+=1