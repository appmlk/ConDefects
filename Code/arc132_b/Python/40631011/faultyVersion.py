n = int(input())
p = list(map(int,input().split()))

def isAscending(l):
    return l == sorted(l)

ans = []

# pattern1: S,S,...
i = p.index(1)
p2 = p[i:] + p[:i]
if isAscending(p2):
    ans.append(i)

# patter2: R,S,S...
p2 = p[::-1]
i = p2.index(1)
p2 = p[i:] + p[:i]
if isAscending(p2):
    ans.append(i+1)

# patter3: S,S..., R
i = p.index(n)
p2 = p[i:] + p[:i]
p2 = p2[::-1]
if isAscending(p2):
    ans.append(i+1)

# patter4: R,S,S..., R
p2 = p[::-1]
i = p2.index(n)
p2 = p2[i:] + p2[:i]
p2 = p2[::-1]
if isAscending(p2):
    ans.append(i+2)

print(min(ans))