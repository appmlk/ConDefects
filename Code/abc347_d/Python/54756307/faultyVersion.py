a,b,C=map(int,input().split())
rev=False
if a<b:
	a,b=b,a
	rev=True
s=bin(C)
S=s[2:]
N=len(S)
A=0
B=0
bit=[]
for i in range(N):
	j=N-1-i
	if S[j]=='1':
		bit.append(i)

if a-b>len(bit) or (a-b)%2!=len(bit)%2:
	exit(print(-1))

cnt=len(bit)
d=a-b
base=b-(cnt-d)//2
next=False
for i in range(60):
	if not i in bit:
		if base>0:
			if a==0 or b==0:
				exit(print(-1))
			A+=2**i
			B+=2**i
			base-=1
			a-=1
	else:
		if d>0:
			if a==0:
				exit(print(-1))
			A+=2**i
			d-=1
			a-=1
		else:
			if next:
				if b==0:
					exit(print(-1))
				B+=2**i
				next=False
				b-=1
			else:
				if a==0:
					exit(print(-1))
				A+=2**i
				next=True
				a-=1

if rev:
	print(B,A)
else:
	print(A,B)
		