from random import randrange
N, M = map(int, input().split())
S = [0]
for k in range(15):
    for a in S[:]:
        S.append(a + 3 ** k)

S, T = S[N:][::-1], S[:N]
s = sum(T)
while (s - M) % N:
    j = randrange(N)
    s += S[-1] - T[j]
    T[j] = S.pop()

print(*[a - (s - M) // N for a in T])