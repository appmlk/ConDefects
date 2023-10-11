N, K = map(int, input().split())
sk = list(str(K))
rk = ''.join(reversed(sk))
if K > int(rk):
    print(0)
    exit()

array = []
for i in range(12):
    array.append(int(rk + '0' * i))
    array.append(int(''.join(sk) + '0' * i))

ans = 0
for a in set(array):
    if a <= N:
        ans += 1
print(ans)
