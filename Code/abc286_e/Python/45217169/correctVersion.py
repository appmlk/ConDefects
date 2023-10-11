import collections

N = int(input())
A = list(map(int,input().split()))

S = [list(input()) for _ in range(N)]

max_value = [ [[0,0] for _ in range(N) ] for _ in range(N) ]

for start in range(N):
    que = collections.deque([[start,A[start]]])

    seen = [-1]*N
    seen[start] = 0

    while que:
        from_i,value = que.popleft()

        for to_i in range(N):
            if S[from_i][to_i]=='Y' and (seen[to_i]==seen[from_i]+1 or seen[to_i]==-1):
                if max_value[start][to_i][1]<value+A[to_i]:
                    max_value[start][to_i] = [seen[from_i]+1,value+A[to_i]]
                
                    seen[to_i] = seen[from_i]+1
                    que.append([to_i,value+A[to_i]])

#print(max_value)
Q = int(input())
ans = []
for _ in range(Q):
    U,V = map(int,input().split())
    if max_value[U-1][V-1] != [0,0]:
        print(*max_value[U-1][V-1])
    else:
        print('Impossible')
    