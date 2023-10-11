N = int(input())

names = []

for _ in range(N):
    first, last = input().split()
    names.append((first, last))

for i in range(N):
    i_first, i_last = names[i]
    is_first_ok, is_last_ok = True, True
    for j in range(N):
        
        if i_first in names[j]:
            is_first_ok = False
        
        if i_last in names[j]:
            is_last_ok = False

    if is_first_ok or is_last_ok:
        continue
    else:
        print("No")
        exit()


print("Yes")