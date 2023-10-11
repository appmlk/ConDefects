def judge1():
    def judge2(u, idx):
        for i in range(N):
            if i == idx:
                continue
            if u in ST[i]:
                return False
        return True
    N = int(input())
    ST = [input().split() for _ in range(N)]
    for i in range(N):
        si, ti = ST[i]
        if judge2(si, i) == False or judge2(ti, i) == False:
            return False
    return True
print('Yes' if judge1() == True else 'No')    