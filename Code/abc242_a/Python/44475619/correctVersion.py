inp=input().split()
A=inp[0]
A=int(A)
B=inp[1]
B=int(B)
C=inp[2]
C=int(C)
X=inp[3]
X=int(X)
D=B-A
if A>=X:
    print(1)
elif B<X:
    print(0)
else:    
    print(C/D)