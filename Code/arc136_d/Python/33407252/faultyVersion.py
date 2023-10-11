def read(): return list(map(int, input().strip().split()))
n, = read()
a = read()
S = [0 for i in range(10**6+5)]
selfpair = 0
for i in a: 
    S[i] = 1
    if all([int(j) < 5 for j in str(i)]):
        selfpair += 1
for j in range(6):
    for i in range(10**6):
        if (i // 10**j) % 10:
            S[i] += S[i-10**j]
ans = 0
for i in a:
    ans += S[999999 - i]
print((ans - selfpair) // 2)