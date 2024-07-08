from collections import defaultdict

def main():
    N = ini()
    
    orders = []
    essential = defaultdict(list)
    for i in range(N):
        t, x = mapint()
        orders.append((t, x))
        
        if t == 1:
            essential[x].append(i)
    
    essential_list = []
    for i in range(N-1, -1, -1):
        t, x = orders[i]
        if t == 2:
            index = float("inf")
            while index > i:
                if not essential[x]:
                    print(-1)
                    return
                index = essential[x].pop()
            essential_list.append(index)
    essential_list = sorted(essential_list)[::-1]
    ans = []
    cnt = 0
    max_cnt = 0
    for i in range(N):
        t, x = orders[i]
        if t == 1:
            if essential_list and essential_list[-1] == i:
                essential_list.pop()
                cnt += 1
                ans.append(1)
            else:
                ans.append(0)
        else:
            max_cnt = max(max_cnt, cnt)
            cnt -= 1
    print(max_cnt)
    for a in ans:
        print(a, end=" ")
    
    
    

def ini(): return int(input())
def mapint(): return map(int, input().split())
def mapint0(): return map(lambda x: int(x)-1, input().split())
def mapstr(): return input().split()
def lint(): return list(map(int, input().split()))
def lint0(): return list(map(lambda x: int(x)-1, input().split()))
def lstr(): return list(input().rstrip())
def errprint(*x): return None if atcenv else print(*x, file=sys.stderr) 

if __name__=="__main__":
    import sys, os
    input = sys.stdin.readline
    atcenv = os.environ.get("ATCODER", 0)

    main()
