B=int(input())
a=-1
for i in range(1,17):
    if i**i==B:
        a=i
        break
print(a)