A,B=map(int,input().split())
L=list(map(int,input().split()))
count=0
for i in range(A):
  if L[i] <= B:
    count+=B
print(count)
