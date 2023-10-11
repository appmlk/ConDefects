n = int(input())
p = list(map(int, input().split()))

cnt = [0]
ans = []

def swap(i):
    p[i], p[i+1] = p[i], p[i+1]
    cnt[0] += 1
    ans.append(i+1)


for i in range(0, 2*n, 2):
    if i == 0:
        if p[0] > p[1]:
            swap(0)
        continue

    if p[i-1] > p[i]:
        if p[i] > p[i+1]:
            swap(i)
    else:
        if p[i] < p[i+1]:
            swap(i-1)
        else:
            if p[i-1] < p[i+1]:
                swap(i-1)
            else:
                swap(i)

print(cnt[0])
if cnt[0] != 0:
    print(*ans)