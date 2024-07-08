n = int(input())
adict = dict(zip(list(range(1,n+1)), list(map(int, input().split()))))
iset = set(adict.keys())
aset = set(adict.values())
last = (iset - aset).pop()
acc = []
while last != -1:
    acc.append(last)
    last = adict[last]
acc.reverse()
print(acc)