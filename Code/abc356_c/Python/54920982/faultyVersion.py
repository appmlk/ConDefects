def main():
    n, m, k = map(int, input().split())
    Tests = []
    for _ in range(m):
        _, *A, r = input().split()
        A = list(map(int, A))
        Tests.append((A, r))

    ans = 0
    for i in range(2**n):
        ok = True
        if i.bit_count() < k:
            continue
        for test in Tests:
            A, r = test
            trueKey = 0
            for a in A:
                trueKey += (i >> (a - 1)) & 1
            if r == "o":
                ok &= k <= trueKey
            else:
                ok &= trueKey < k
        if ok:
            ans += 1
    print(ans)


if __name__ == "__main__":
    main()
