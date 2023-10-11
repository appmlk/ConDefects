def get_min(s):
    mask = 1
    val = 0
    for ch in s[::-1]:
        if ch == '1':
            val += mask
        mask <<= 1
    return val

def I():
    return input()

def II():
    return int(input())

def solve():
    s = I()
    n = II()
    min_val = get_min(s)
    mask = 1 << (len(s) - 1)
    if min_val > n:
        return -1
    ans = 0
    for ch in s:
        if ch == '?' and min_val + mask <= n:
            ans += mask
            n -= mask
        if ch == '1':
            min_val -= mask
            ans += mask
            n -= mask
        mask >>= 1
    return ans

print(solve())