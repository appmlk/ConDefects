X,A,D,N=map(int,input().split())

if D==0:
    if A==X:
        print(0)
    else:
        print(abs(X-A))
    exit()


X-=A
a0=0
an=D*(N-1) 
if D>=1:
    if not a0<=X<=an:
        print(min(abs(a0-X),abs(an-X)))
    else:
        print(min(X%D,D-X%D))
else:
    if not an<=X<=a0:
        print(min(abs(a0-X),abs(an-X)))
    else:
        print(min(X%D,D-X%D))
