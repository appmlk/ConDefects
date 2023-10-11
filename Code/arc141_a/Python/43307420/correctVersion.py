def make_div(n):
    lower_divisors , upper_divisors = [], []
    i = 1
    while i*i <= n:
        if n % i == 0:
            lower_divisors.append(i)
            if i != n // i:
                upper_divisors.append(n//i)
        i += 1
    return lower_divisors + upper_divisors[::-1]

T = int(input())
for _ in range(T):
    N = int(input())
    res = make_div(len(str(N)))
    ans = int("9" * (len(str(N)) - 1))
    for i in res:
        if i == len(str(N)):
            continue
        cnt = len(str(N)) // i
        num = int(str(N)[:i] * cnt)
        if num > N:
            num = int(str(N)[:i]) - 1
            num = int(str(num) * cnt)
        ans = max(ans, num)
    print(ans)