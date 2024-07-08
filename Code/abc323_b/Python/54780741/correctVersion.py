from collections import Counter
N=int(input())
S=[input() for _ in range(N)]
win_result=[]
for i in range(N):
    counter=Counter(S[i])
    win_result.append(counter["o"])
c=N-1
rank=[]
for i in range(N):
    for j in range(N):
        if win_result[j]==c:
            rank.append(j+1)
    c-=1
print(*rank, sep=" ")