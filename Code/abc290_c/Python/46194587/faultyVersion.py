N, K = map(int, input().split())
A = sorted(list(map(int, input().split())))

mex = 0
count = 0
for a in A:
    if mex == a and count < K:
        mex += 1
        count += 1
    else:
        break
print(mex)

