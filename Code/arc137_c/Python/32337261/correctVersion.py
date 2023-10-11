import sys
sys.setrecursionlimit(500005)
input = sys.stdin.readline
read_str = lambda: input().strip()
read_num = lambda: int(input())
read_nums = lambda: map(int, input().split())
read_list = lambda: list(map(int, input().split()))

N = int(3e5) + 10
mod = 998244353

def solve():
    n = read_num()
    nums = read_list()
    if nums[-1] == n - 1:
        print('Bob')
    elif nums[-2] + 1 < nums[-1]:
        print('Alice')
    elif (nums[-1] - n + 1) % 2:
        print('Alice')
    else:
        print('Bob')
    return

solve()