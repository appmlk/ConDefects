n,m,p=map(int,input().split())
count=0
while m<=n:
    m+=p
    count+=1
print(count)