
Datos = input()
Data = [int (num) for num in Datos.split()]
if Data[0]*2==Data[1] or (Data[0]*2)+1==Data[1]:
    print("Yes")
else:
    print("No")
    