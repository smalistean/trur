select p.id, p.position, p.lat, p.lng
from TrurApp.Point p
join TrurApp.Transport t on t.Id=p.TransportId
where t.TransportTypeId=2 and t.Id=51
order by p.position

select tt.id, tt.name, t.id, t.name
from TrurApp.TransportType tt
join TrurApp.Transport t on t.TransportTypeId=tt.id