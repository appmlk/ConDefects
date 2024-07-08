H, W, K = list(map(int, input().split()))
N = (H+1)*(W+1)
ban = 2*(10**5)
line = [ban]*(2*N)
for h in range(H):
    dh = h*(W+1) + N
    for w, s in enumerate(input()):
        if s == 'o':
            line[dh+w] = 0
            line[w*(H+1)+h] = 0
        if s == '.':
            line[dh+w] = 1
            line[w*(H+1)+h] = 1
# print(line)
tmp = sum(line[:K])
ans = tmp
for i in range(K, 2*N):
    tmp -= line[i-K]
    tmp += line[i]
    ans = min(ans, tmp)

if ans < ban:
    print(ans)
else:
    print(-1)