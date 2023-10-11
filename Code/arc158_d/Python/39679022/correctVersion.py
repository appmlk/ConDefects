import random


def solve(n, p):
    while True:
        a, b = random.sample(range(2, p), 2)
        rig = (1 + pow(a, 3 * n, p) + pow(b, 3 * n, p)) % p
        lef = (
            (1 + a + b)
            % p
            * (1 + pow(a, n, p) + pow(b, n, p))
            % p
            * (1 + pow(a, 2 * n, p) + pow(b, 2 * n, p))
            % p
        )
        if lef == 0 or rig == 0:
            continue
        x = rig * pow(lef, p - 2, p) % p
        y = x * a % p
        z = x * b % p
        return sorted((x, y, z))


def test(T=100000):
    for _ in range(T):
        n = random.randint(1, 10**9)
        p = 5
        x, y, z = solve(n, p)
        # print(x, y, z)
        assert (
            (x + y + z)
            % p
            * (pow(x, n, p) + pow(y, n, p) + pow(z, n, p))
            % p
            * (pow(x, 2 * n, p) + pow(y, 2 * n, p) + pow(z, 2 * n, p))
            % p
        ) == (pow(x, 3 * n, p) + pow(y, 3 * n, p) + pow(z, 3 * n, p)) % p


def main():
    T = int(input())
    for _ in range(T):
        n, p = map(int, input().split())
        x, y, z = solve(n, p)
        print(x, y, z)
        # assert (
        #     (x + y + z)
        #     % p
        #     * (pow(x, n, p) + pow(y, n, p) + pow(z, n, p))
        #     % p
        #     * (pow(x, 2 * n, p) + pow(y, 2 * n, p) + pow(z, 2 * n, p))
        #     % p
        # ) == (pow(x, 3 * n, p) + pow(y, 3 * n, p) + pow(z, 3 * n, p)) % p


main()
# test()
