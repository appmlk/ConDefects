N=int(input())
q=[]
for i in range(N):
    l,r = list(map(int, input().split()))

    q.append((l,r))

ans=[]
q.sort()
now_l,now_r = q[0]
for i in range(N):

    l,r = q[i]

    if l<=now_r:
        now_r = max(r,now_r)
    else:
        ans.append((now_l,now_r))
        now_l,now_r = q[i]
ans.append((now_l,now_r))
for i in ans:
    print(*i)