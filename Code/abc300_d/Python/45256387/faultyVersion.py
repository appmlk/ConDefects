n = 10**12

def primes(n):
    ass = []
    is_prime = [True] * (n+1)
    is_prime[0] = False
    is_prime[1] = False
    for i in range(2, int(n**0.5)+1):
        if not is_prime[i]:
            continue
        for j in range(i*2, n+1, i):
            is_prime[j] = False
    for i in range(len(is_prime)):
        if is_prime[i]:
            ass.append(i)
    return ass

x = int((n/12)**0.5)
p = primes(x)
m = len(p)
ans = 0
for b in range(1,m-1):
    for c in range(b + 1, m):
        if p[b] * p[c]**2 > n:
            break
        for a in range(b):
            if p[a] **2 * p[b] * p[c]**2 > n:
                break

            else:
                ans += 1
print(ans)
