#提出コード------------------------------
import itertools
import sys
sys.setrecursionlimit(10**6)
def IN(): return int(input())
def IS(): return input() 
def INL(): return list(map(int,input().split()))
def ITN(): return map(int,input().split())

N,M = ITN()
Price_and_func = [list(map(int,input().split())) for _ in range(N)]

def condition(l1,l2):
    if l1[0] >= l2[0]:
        if (set(l1[2:]) & set(l2[2:])) == set(l1[2:]):
            if len(l2) > len(l1) or (l1 > l2):
                return True
            else:
                return False
        else:
            return False
    else:
        return False


for i in range(N):
    for j in range(N):
        if i == j: continue
        if condition(Price_and_func[i],Price_and_func[j]) == True:
            print('Yes')
            exit()

print('No')