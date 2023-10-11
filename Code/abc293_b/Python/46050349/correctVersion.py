# B
n = int(input())
a = list(map(int, input().split()))
#n = 5
#a = [3, 1, 4, 5, 4]
#print(a)
call = set()
for i in range(n):
    #print(i)
    #print(a[i])
    if i+1 in call:
        pass
    else:
        call.add(a[i])
#print(sorted(call))
no_call =[]
for i in range(1, n+1):
    if i not in call:
        #print(i)
        no_call.append(i)
print(len(no_call))
print(*no_call)
