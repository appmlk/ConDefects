n = int(input())
As = [int(x) for x in input().split()]

if As[-1]-As[-2] > 1:
    print("Alice")
else:
    if As[-1]-(n-1)%2==1:
        print("Alice")
    else:
        print("Bob")