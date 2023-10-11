if __name__ == '__main__':
    s = str(input())
    n = int(input())
    m = len(s)
    res = 0
    for i in range(m):
        if s[i] == "1":
            res += (1 << (m - 1 - i))
    if res > n:
        res = -1
    else:
        for i in range(m - 1, -1, -1):
            if s[i] == "?" and res + (1 << (m - 1 - i)) <= n:
                res += (1 << (m - 1 - i))
    print(res)