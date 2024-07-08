import math

MOD = 998244353


def main():
    n = int(input())
    k = len(str(n))
    y = pow(10**k - 1, MOD - 2, MOD)
    ans = ((n % MOD) * (pow(10, n * k, MOD) - 1) * y) % MOD
    print(ans)


if __name__ == "__main__":
    main()
