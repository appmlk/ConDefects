import sys
from typing import Counter

inf = 1e9

def input():
    return sys.stdin.readline().strip()
    
def solution(nums):
    MOD = 10**56 + 9
    N = len(nums)
    nums = [x % MOD for x in nums]
    count = Counter(nums)
    ans = 0
    for i in range(N):
        for j in range(N):
            ans += count[(nums[i] * nums[j]) % MOD]
    
    print(ans)

def main():
    n = int(input())
    solution([int(input()) for _ in range(n)])
        
if __name__ == "__main__":
    main()