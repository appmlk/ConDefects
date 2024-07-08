B = int(input())

for A in range(1,16) :
    pow = 1
    for j in range(A) :
        pow *= A
        
    if pow == B :
        print(A)
        exit()

print(-1)