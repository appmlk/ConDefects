def factorization(n):
    arr = []
    temp = n
    for i in range(2, int(-(-n**0.5//1))+1):
        if temp%i==0:
            cnt=0
            while temp%i==0:
                cnt+=1
                temp //= i
            arr.append([i, cnt])

    if temp!=1:
        arr.append([temp, 1])

    if arr==[]:
        arr.append([n, 1])

    return arr
n=int(input())
A=list(map(int,input().split()))
L=[]
s=set()
for i in range(n):
	if A[i] not in s:
		L.append(A[i])
		s.add(A[i])
L=sorted(L)
ans=2
first=0
k=0
if len(L)!=1:
  first=L[1]-L[0]
  p=factorization(first)
  k=len(p)
else:
	ans=1
for i in range(k):
	if ans==1:
		break
	for j in range(2,len(L)):
		other=L[j]-L[0]
		if other%p[i][0]!=0:
			break
		if j==len(L)-1:
			ans=1
if first==1:
	print(2)
elif len(L)==2:
	print(1)
else:
	print(ans)