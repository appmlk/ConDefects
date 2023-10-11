from collections import Counter


def solve_odd(a, m):
    c = Counter(a)
    return any(cnt%2 for cnt in c.values())

def solve_even(a, m):
    mm = m//2
    c = Counter(v%mm for v in a)
    if any(cnt%2 for cnt in c.values()):
        return True

    r = 0
    c = Counter(a)
    for v in set(a):
        if v >= mm:
            continue
        r += c[v]%2
    return r % 2


n,m = map(int,input().split())

a = list(map(int,input().split()))

res = solve_even(a, m) if m%2 == 0 else solve_odd(a, m)
print('Alice' if res else 'Bob')