import sys

import numba
import numpy as np


def main() -> None:
    readline = sys.stdin.readline
    M, N = map(int, readline().split())
    xs = np.array(list(n - 1 for n in map(int, readline().split())))

    print(numba_main(M, N, xs))


@numba.jit(nopython=True, cache=True)
def numba_main(M: int, N: int, xs: np.ndarray) -> np.int64:
    inverse_xs = [0 for m in range(M)]
    for i, x in enumerate(xs):
        inverse_xs[x] |= 1 << i

    transition = [
        [
            allowed ^ (1 << num) | inverse_xs[num]
            for num in range(M)
            if (allowed >> num) & 1
        ]
        for allowed in range(2**M)
    ]

    memo = np.zeros((N + 1, 2**M), dtype=np.int64)
    memo[0, 2**M - 1] = 1
    for prev_length in range(N):
        for allowed_curr in range(1, 2**M):
            for allowed_next in transition[allowed_curr]:
                memo[prev_length + 1, allowed_next] += memo[prev_length, allowed_curr]
                memo[prev_length + 1, allowed_next] %= 998244353
    return np.sum(memo[N] % 998244353)  # type: ignore


if __name__ == "__main__":
    main()
