import math

MOD = 998244353


def main():
    n = int(input())
    k = math.floor(math.log10(n)) + 1
    y = pow(10**k - 1, MOD - 2, MOD)
    ans = ((n % MOD) * (pow(10, n * k, MOD) - 1) * y) % MOD
    print(ans)


if __name__ == "__main__":
    main()
