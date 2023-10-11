n = int(input())
S = input()

def f(T):
    U = [c for c in reversed(T)]
    for i in range(len(U)):
        U[i] = 'd' if U[i] == 'p' else 'p'
    return ''.join(U)

l = 0
while l < n and S[l] != 'd':
    l += 1

ans = S
for r in range(l+1, n+1):
    ans = min(ans, S[:l] + f(S[l:r]) + S[r:])
print(ans)