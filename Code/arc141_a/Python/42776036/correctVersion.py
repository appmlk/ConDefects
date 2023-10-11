import sys

read = sys.stdin.read
readline = sys.stdin.readline
readlines = sys.stdin.readlines


T = int(readline())

for _ in range(T):
    N = readline().rstrip()

    LEN = len(N)
    ans = int('9' * (LEN - 1))

    if LEN % 2:
        LEN += 1

    for i in range(1, LEN // 2 + 1):
        num1 = N[:i]
        num2 = str(max(int(num1) - 1, 1))

        tmp1 = num1 * 2
        tmp2 = num2 * 2

        while True:
            if int(tmp1) <= int(N):
                ans = max(ans, int(tmp1))
                tmp1 += num1
            else:
                break
        while True:
            if int(tmp2) <= int(N):
                ans = max(ans, int(tmp2))
                tmp2 += num2
            else:
                break
    
    print(ans)
