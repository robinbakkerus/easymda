package jrb.blazeds.grpc;

import io.grpc.stub.StreamObserver;
import jrb.blazeds.klantbeeld.model.Klantbeeld;
import jrb.blazeds.klantbeeld.service.GetKlantbeeld;
import jrb.blazeds.klantbeeld.service.GetKlantbeeldImpl;
import jrb.blazeds.klantbeeld.service.SelectKlantbeeld;
import jrb.blazeds.klantbeeld.service.SelectKlantbeeldMapper;
import jrb.grpc.blazeds.GetKlantbeeldGrpc;
import jrb.grpc.blazeds.KlantbeeldMsg;
import jrb.grpc.blazeds.SelectKlantbeeldMsg;

public class GetKlantbeeldGrpcImpl extends GetKlantbeeldGrpc.GetKlantbeeldImplBase {

	private GetKlantbeeld klantbeeldService = new GetKlantbeeldImpl();

	@Override
	public void getKlantbeeld(SelectKlantbeeldMsg request, StreamObserver<KlantbeeldMsg> responseObserver) {
		System.out.println("getKlantbeeld called ...");
		final SelectKlantbeeld selectKlantbeeld = SelectKlantbeeldMapper.fromProtobuf(request);
		final Klantbeeld klantbeeld = this.klantbeeldService.getKlantbeeld(selectKlantbeeld);
//		final KlantbeeldMsg response = KlantbeeldMapper.toProtobuf(klantbeeld);
//		responseObserver.onNext(response);
//		responseObserver.onCompleted();
	}
}
