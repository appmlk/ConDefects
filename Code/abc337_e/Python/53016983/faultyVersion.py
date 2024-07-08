import sys
input = lambda: sys.stdin.readline().rstrip()
ii = lambda: int(input())
mi = lambda: map(int, input().split())
li = lambda: list(mi())
inf = 2 ** 63 - 1
mod = 998244353
hash_mod = 2147483647
dpos4 = ((1, 0), (0, 1), (-1, 0), (0, -1))
dpos8 = ((0, -1), (1, -1), (1, 0), (1, 1), (0, 1), (-1, 1), (-1, 0), (-1, -1))
def main():
    N = ii()
    M = (N-1).bit_length()
    print(M,flush=True)
    for i in range(M):
        a = []
        for j in range(N):
            if (j >> i) & 1:
                a.append(j+1)

        print(len(a),*a,flush=True)

    S = input()
    ans = int(S,2)
    print(ans,flush=True)
if __name__ == '__main__':
    main()  