result="Yes"
N=int(input())
lis=list(map(int,input().split(' ')))

for i in range(len(lis)-1):
    if lis[i]!=lis[i+1]:
        result="No"

print(result)