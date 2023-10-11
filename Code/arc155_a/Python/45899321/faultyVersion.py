def is_palindrome(s):
    return s == s[::-1]

def solve(n, k, s):
    k %= 2 * n
    s_inv = s[::-1]
    if k < n:
        t = s_inv[:k]
    else:
        t = s_inv + s_inv[-(k-n):]
    return "Yes" if all(is_palindrome(x) for x in [s+t, t+s]) else "No"

t = int(input())
for _ in range(t):
    n, k = map(int, input().split())
    s = input()
    print(solve(n, k, s))
    