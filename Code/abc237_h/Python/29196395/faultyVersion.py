S = input()
N = len(S)

from collections import defaultdict

s_dict = defaultdict(list)
for i in range(N-1,-1,-1):
    s_dict[S[i]].append(i)

def my_check(i,j):
    for k in range((j-i+1)//2):
        if S[i+k] != S[j-k]:
            return False
    return True

text_set = set()
for i in range(N):
    for j in s_dict[S[i]]:
        if j < i:
            break
        if my_check(i,j):
            text_set.add(S[i:j+1])

text_list = list(text_set)
text_list.sort(key=len)

edge_list = defaultdict(list)
n = len(text_list)
for i in range(n-1):
    for j in range(i+1,n):
        if text_list[i] in text_list[j]:
            edge_list[i].append(j)

# simple bipartite graph
b_to_a_list = [-1] * n
used = [0] * n
def my_dfs(a):
    used[a] = 1
    for b in edge_list[a]:
        w = b_to_a_list[b]
        if w == -1 or (used[w] == 0 and my_dfs(w)):
            b_to_a_list[b] = a
            return True
    return False

ans = 0
for a in range(n):
    used = [0] * n
    if my_dfs(a):
        ans += 1
    else:
        break

print(n-ans)
