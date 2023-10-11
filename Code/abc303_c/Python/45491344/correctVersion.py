from collections import defaultdict
N,M,H,K = map(int,input().split())
S = list(input())
item = defaultdict(int)
for i in range(M):
    x,y = map(int,input().split())
    item[(x,y)] = 1
    
now = [0,0]
for i in range(N):
    move = S[i]
    if move == "R":
        now[0] += 1
    elif move == "L":
        now[0] -= 1 
    elif move == "U":
        now[1] += 1
    elif move == "D":
        now[1] -= 1
    H -= 1
    if H < 0:
        print("No")
        break
    if item[(now[0],now[1])] == 1 and  H < K:
        H = K
        item[(now[0],now[1])] = 0

else:
    print("Yes")