def main():
    n = int(input())

    memo = {i: i for i in range(2, n + 1)}
    ans = [1]
    for i in range(2, n - 1):
        ans.append(i)
        memo[i] -= 2
    p = n
    d = -1
    while memo[p]:
        ans.append(p)
        memo[p] -= 1
        p += d
        if not memo.get(p, 0):
            d = -d
            p += d * 2
        if d == 1 and p == n - 1:
            p += 1
    for i in range(n - 2, 1, -1):
        ans.append(i)
    print(*ans)

if __name__ == '__main__':
    main()
