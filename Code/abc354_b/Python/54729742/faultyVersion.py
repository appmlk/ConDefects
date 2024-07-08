N = int(input())
list = []
T = 0
for i in range(N):
    S, C = input().split()
    list.append([S, int(C)])
    T += int(C)
name = []
for j in range(N):
    name.append(list[j])
name.sort()
m = T % N
print(list[m][0])